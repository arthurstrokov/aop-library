/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.gmail.arthurstrokov;

import com.gmail.arthurstrokov.service.Countable;
import com.gmail.arthurstrokov.service.TrackTime;
import org.springframework.stereotype.Service;

@Service
public class Library {
    @TrackTime
    @Countable
    public boolean someLibraryMethod() {
        return true;
    }
}
