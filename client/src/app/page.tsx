"use client"

import { useState } from "react"
import UserForm from "../components/UserForm/UserForm"
import Image from "next/image"
import combination from "@public/combination.png"
export default function Landing() {
  const [isLoggedIn, setIsLoggedIn] = useState(false)
  return (
    <>
      {isLoggedIn ? (
        <div className="">
          <main></main>
        </div>
      ) : (
        <div className="min-h-screen w-full">
          <main className="flex flex-row items-center">
            <UserForm />
            <div className="hidden md:flex min-h-screen justify-center flex-col items-center w-full">
              <Image src={combination} alt="combination" className="w-3/5" />
            </div>
          </main>
        </div>
      )}
    </>
  )
}
