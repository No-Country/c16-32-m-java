package com.c1632mjava.c1632mjava.Domain.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SpotifyUser {
    //private String country;
    private String displayName;
    private String email;
    //private ExplicitContent explicitContent;
    //private ExternalUrls externalUrls;
    //private Followers followers;
    //private String href;
    //private String id;
    //private List<Image> images;
    //private String product;
    //private String type;
    //private String uri;
}

@Data
@AllArgsConstructor
class ExplicitContent{
    private boolean filterEnabled;
    private boolean filterBlocked;
}

@Data
@AllArgsConstructor
class ExternalUrls{
    private String spotify;
}

@Data
@AllArgsConstructor
class Followers{
    private String href;
    private int total;
}

@Data
@AllArgsConstructor
class Image{
    private String Url;
    private int height;
    private int width;
}
