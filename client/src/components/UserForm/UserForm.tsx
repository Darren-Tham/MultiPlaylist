import React, { FormEvent } from "react";
import Image from "next/image";
import logo from "../../../public/multilogo.png";
type Props = {
  name: string;
  redirect: string;
};

export default function UserForm({ name, redirect }: Readonly<Props>) {
  async function onSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    const formData = new FormData(event.currentTarget);
    const response = await fetch("/register", {
      method: "POST",
    });
  }

  return (
    <div className="flex items-center justify-center w-full h-screen">
      <div className="w-4/5">
        <Image src={logo} alt="logo" />
        <h1 className="font-bold text-2xl mb-2">{name}</h1>
        <form onSubmit={onSubmit} className="flex gap-2 flex-col mb-3">
          <label
            className="block text-gray-900 text-lg font-light"
            htmlFor={"email"}
          >
            Email
          </label>
          <input
            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            id="email"
          />
          <label
            className="block text-gray-900 text-lg font-light"
            htmlFor={"password"}
          >
            Password
          </label>
          <input
            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            id="password"
          />
          <input
            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
            type="submit"
            value={name}
          />
        </form>
        <p className="text-center text-lg text-sky-400 hover:text-blue-800 mb-4">
          {redirect}
        </p>
      </div>
    </div>
  );
}
