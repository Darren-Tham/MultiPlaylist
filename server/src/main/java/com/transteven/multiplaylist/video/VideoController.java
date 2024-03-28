package com.transteven.multiplaylist.video;

import com.transteven.multiplaylist.video.VideoService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/video")
public class VideoController {

  private final VideoService videoService;

  @Autowired
  public VideoController(final VideoService videoService) {
    this.videoService = videoService;
  }

  @GetMapping(value = "/{userID}/{playlistID}/{videoID}")
  public ResponseEntity<Video> getVideo(
    @PathVariable int userID,
    @PathVariable int playlistID,
    @PathVariable int videoID
  ) {
    return ResponseEntity.ok(videoService.getVideoById(videoID));
  }

  @PostMapping("/{userID}/{playlistID}/add")
  public ResponseEntity<Video> addVideo(
    @PathVariable int userID,
    @PathVariable int playlistID,
    @RequestBody final Video video
  ) {
    videoService.addVideo(video);
    return ResponseEntity.ok(video);
  }

  @DeleteMapping("/{userID}/{playlistID}/{videoID}")
  public ResponseEntity<String> deleteVideo(
    @PathVariable int userID,
    @PathVariable int playlistID,
    @PathVariable int videoID
  ) {
    videoService.deleteVideo(videoID);
    return ResponseEntity.ok("Video deleted successfully");
  }
}
