
"use client"
import React from 'react';
import Image from 'next/image'
import logo from "../../../public/multilogo.png"
type Playlist = {
  id: number;
  name: string;
};

// Dummy data for the sake of example
const playlists: Playlist[] = [
  { id: 1, name: "User's Playlist 1" },
  { id: 2, name: "User's Playlist 2" },
  // Add more playlists as needed
];

const PlaylistPage: React.FC = () => {
  return (
    <div className="min-h-screen flex flex-col md:flex-row bg-gray-50 text-gray-900">
      <div className="w-full md:w-1/4 p-4 space-y-6 bg-white shadow-md">
        <div><Image src={logo} alt="logo"/> </div>
        <button className="flex items-center gap-2 text-black border-2 shadow p-2 rounded-md hover:bg-gray-200 ">
          <span>Add Playlist</span>
          <span className="text-2xl">+</span>
        </button>
        <nav>
          {playlists.map((playlist) => (
            <div key={playlist.id} className="p-3 hover:bg-gray-200 rounded-md transition-colors cursor-pointer">
              <span>{playlist.name}</span>
            </div>
          ))}
        </nav>
      </div>

      <div className="flex-1 p-4 overflow-auto">
        <div className="flex justify-between items-center mb-4">
          <h2 className="text-2xl font-bold hidden md:block">{`{email}'s Playlist id `}</h2>
          <button className=" hover:bg-gray-200 text-black border-2 py-2 px-4 rounded-full shadow-md transition-colors">
            LOGOUT
          </button>
        </div>
        {/* Content for playlist items goes here */}
      </div>
    </div>
  );
};

export default PlaylistPage;

