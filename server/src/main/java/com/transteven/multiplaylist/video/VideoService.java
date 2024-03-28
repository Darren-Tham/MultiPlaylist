package com.transteven.multiplaylist.video;

import com.transteven.multiplaylist.video.dao.VideoDAO;
import org.springframework.stereotype.Service;

@Service
public class VideoService {

  private final VideoDAO videoDAO;

  public VideoService(final VideoDAO videoDAO) {
    this.videoDAO = videoDAO;
  }

  public void addVideo(Video video) {
    videoDAO.addVideo(video);
  }

  public Video getVideoById(final int id) {
    return videoDAO
      .getVideoById(id)
      .orElseThrow(() ->
        new IllegalStateException("Video with id " + id + " does not exist")
      );
  }

  public void deleteVideo(final int id) {
    videoDAO.deleteVideo(id);
  }
}
