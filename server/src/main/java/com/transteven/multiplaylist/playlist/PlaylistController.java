package com.transteven.multiplaylist.playlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {

  private final PlaylistService playlistService;

  @Autowired
  public PlaylistController(final PlaylistService playlistService) {
    this.playlistService = playlistService;
  }

  @PostMapping("/{userID}/create")
  public ResponseEntity<Playlist> createPlaylist(
    @PathVariable int userID,
    @RequestBody final Playlist playlist
  ) {
    playlistService.createPlaylist(playlist);
    return ResponseEntity.ok(playlist);
  }

  @DeleteMapping("/{userID}/{playlistID}/delete")
  public ResponseEntity<String> deletePlaylist(
    @PathVariable int userID,
    @PathVariable int playlistID
  ) {
    playlistService.deletePlaylist(playlistID);
    return ResponseEntity.ok("Playlist deleted successfully");
  }
}
