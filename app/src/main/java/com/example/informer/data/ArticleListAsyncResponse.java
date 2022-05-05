package com.example.informer.data;

import java.util.ArrayList;

public interface ArticleListAsyncResponse {
    void processFinish(ArrayList<Article> articles);
}
//don't know much about it but as much i know it is used to be sure that we have downloaded all data from internet
// if we dont do so it may be possible sometime we will get everything empty
