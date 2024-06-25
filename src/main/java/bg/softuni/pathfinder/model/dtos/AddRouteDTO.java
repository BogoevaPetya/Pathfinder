package bg.softuni.pathfinder.model.dtos;

import bg.softuni.pathfinder.model.enums.Level;

import java.io.File;

public class AddRouteDTO {
    private String name;
    private String description;
    private File gpxCoordinates;
    private Level level;
    private String videoUrl;

    public AddRouteDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public File getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(File gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }
}
