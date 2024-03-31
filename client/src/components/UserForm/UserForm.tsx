import Image from "next/image";
import Link from "next/link";
import { ChangeEvent, Dispatch, SetStateAction, useRef, useState } from "react";
import logo from "@public/multilogo.png";
import { useRouter } from "next/navigation";
import { initialSetUp } from "../../lib/Auth";
type Props = {
  name: string;
  redirect: string;
  onToggleHasAccount: () => void;
  hasAccount: boolean;
};

type User = {
  email: string;
  password: string;
};
export default function UserForm({
  name,
  redirect,
  onToggleHasAccount,
  hasAccount,
}: Readonly<Props>) {
  const [email, setEmail] = useState("");
  const passwordRef = useRef<HTMLInputElement | null>(null);
  const [loginFailed, setLoginFailed] = useState(false);
  const [remember, setRemember] = useState(false);
  function setValueWithNoSpaces(
    e: ChangeEvent<HTMLInputElement>,
    setValue: Dispatch<SetStateAction<string>>
  ) {
    const { value } = e.target;
    if (!value.includes(" ")) {
      setValue(value);
    }
  }
  function getUser(): User {
    if (passwordRef.current === null) {
      throw Error("passwordRef should not be null.");
    }
    return {
      email,
      password: passwordRef.current.value,
    };
  }
  async function addUser() {
    const user = getUser();
    const response = await fetch("http://localhost:8443/api/user/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(user),
    });
    const customerId = await response.text();
    return parseInt(customerId);
  }

  async function emailExists() {
    const response = await fetch(
      `http://localhost:8443/api/user/email_exists/${email}`
    );
    const data = await response.text();
    return data === "true";
  }
  const router = useRouter();
  return (
    <div className="flex items-center justify-center w-full h-screen">
      <div className="w-4/5 mt-2">
        <Image src={logo} alt="logo" className="mb-2" />
        <h1 className="font-bold text-2xl mb-2">{name}</h1>
        <form className="flex gap-2 flex-col mb-3">
          <label
            className="block text-gray-900 text-lg font-light"
            htmlFor={"email"}
          >
            Email
          </label>
          <input
            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            id="email"
            value={email}
            onChange={(e) => setValueWithNoSpaces(e, setEmail)}
            type="text"
          />
          <label
            className="block text-gray-900 text-lg font-light label"
            htmlFor={"password"}
          >
            Password
          </label>
          <input
            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            id="password"
            ref={passwordRef}
            type="password"
          />
          <input
            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline transform hover:scale-[103%] transition duration-300 ease-in-out"
            type="submit"
            value={name}
            onClick={async (e) => {
              const password = passwordRef.current?.value;
              if (!hasAccount) {
                e.preventDefault();
                await addUser();
              } else {
                if (email === "") {
                  alert("Email cannot be empty.");
                  return;
                }
                if (password === "") {
                  alert("Password cannot be empty.");
                  return;
                }
                const response = await fetch(
                  `http://localhost:8443/api/user/login/${email}/${password}`
                );
                if (response.ok) {
                  const data = await response.json();
                  initialSetUp(data, remember); // Set Up Cookies Authentication
                  setLoginFailed(false);
                  router.push("/playlists");
                } else {
                  setLoginFailed(true);
                }
              }
            }}
          />
        </form>
        <button
          className="text-center text-lg text-sky-400 hover:text-blue-800 mb-4 transition duration-300 ease-in-out cursor-pointer bg-transparent border-none p-0"
          onClick={onToggleHasAccount}
          type="button"
        >
          {redirect}
        </button>
      </div>
    </div>
  );
}
