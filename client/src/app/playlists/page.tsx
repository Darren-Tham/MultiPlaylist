
import React from 'react';
import Image from 'next/image'
import logo from "@public/multilogo.png"

type Playlist = {
  id: number;
  name: string;
};

// Dummy data for the sake of example
const playlists: Playlist[] = [
  { id: 1, name: "Playlist 1" },
  { id: 2, name: "Playlist 2" },
  // Add more playlists as needed
];

const PlaylistPage: React.FC = () => {
  return (
    <div className="min-h-screen flex flex-col md:flex-row bg-gray-50 text-gray-900">
      <aside className="w-full md:w-1/4 p-4 space-y-6 bg-white shadow-md">
        <div className="space-y-4">
          <div className="flex justify-center md:justify-start">
            <Image src={logo} alt="logo"/>
          </div>
          <div className="flex flex-col space-y-3">
            <button className="flex items-center justify-center gap-2 text-black border-2 shadow p-2 rounded-md hover:bg-gray-200">
              <span>Add Playlist</span>
              <span className="text-2xl">+</span>
            </button>
            <button className="w-full md:w-auto hover:bg-gray-200 text-black border-2 py-2 px-4 rounded-full shadow-md transition-colors">
              LOGOUT
            </button>
          </div>
        </div>
        <nav>
          {playlists.map((playlist) => (
            <div key={playlist.id} className="p-3 hover:bg-gray-200 rounded-md transition-colors cursor-pointer">
              <span>{playlist.name}</span>
            </div>
          ))}
        </nav>
      </aside>
      <main className="flex-1 p-4 overflow-auto">
        <div className="flex justify-between items-center mb-4">
          <h2 className="text-2xl font-bold">{`Playlist ID`}</h2>
          <button className="hidden md:inline-block hover:bg-gray-200 text-black border-2 py-2 px-4 rounded-md shadow-md transition-colors">
            PLAY
          </button>
        </div>
        {/* Content for playlist items goes here */}
      </main>
    </div>
  );
};

export default PlaylistPage;

