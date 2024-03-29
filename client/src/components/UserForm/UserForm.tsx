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
        <h1 className="text-3xl">{name}</h1>
        <form onSubmit={onSubmit} className="flex gap-2 flex-col">
          <label htmlFor={"email"}>Email</label>
          <input className="gap-4 border-slate-500 border-[1px]" id="email" />
          <label htmlFor={"password"}>Password</label>
          <input className="border-slate-500 border-[1px]" id="password" />
          <input className="border-[1px] border-slate-500" type="submit" />
        </form>
        <p className="text-sky-500">{redirect}</p>
      </div>
    </div>
  );
}
