/**
 * Copyright 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 package com.google.firebase.example.fireeats.util;

import android.content.Context;

import com.google.firebase.example.fireeats.R;
import com.google.firebase.example.fireeats.model.Quote;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Utilities for Quotes.
 */
public class QuoteUtil {

    private static final String[] quote_urls = {
            "https://cdn.pixabay.com/photo/2012/04/25/00/35/speech-bubble-41392_960_720.png",
            "https://cdn.pixabay.com/photo/2012/04/16/12/35/callout-35799_960_720.png",
            "https://cdn.pixabay.com/photo/2012/04/12/19/48/callout-30368_960_720.png",
            "https://cdn.pixabay.com/photo/2018/02/28/02/29/art-3187088_960_720.jpg",
            "https://cdn.pixabay.com/photo/2016/06/02/10/14/yada-yada-1430679_960_720.jpg",
            "https://cdn.pixabay.com/photo/2016/06/03/07/27/yada-yada-1432923_960_720.jpg",
            "https://cdn.pixabay.com/photo/2016/01/12/12/40/colored-pencil-1135328_960_720.png",
            "https://cdn.pixabay.com/photo/2015/12/07/01/41/pen-1080215_960_720.jpg"
    };

    private static final int MAX_IMAGE_NUM = 8;

    /**
     * Create a random Quote POJO.
     */
    public static Quote getRandom(int i, Context context) {

        Quote quote = new Quote();

        // Categories (first element is 'Any')
        String[] categories = context.getResources().getStringArray(R.array.categories);
        categories = Arrays.copyOfRange(categories, 1, categories.length);

        // Quotes (first element is 'Any')
        String[] quotes = context.getResources().getStringArray(R.array.quotes);
        quotes = Arrays.copyOfRange(quotes, 1, quotes.length);

        quote.setName(quotes[i]);
        quote.setCategory(categories[i]);
        quote.setPhoto(getRandomImageUrl());

        return quote;
    }
    
    /**
     * Get a random image.
     */
    public static String getRandomImageUrl() {

        Random random = new Random();
        // Integer between 1 and MAX_IMAGE_NUM (inclusive)
        int i = random.nextInt(MAX_IMAGE_NUM);
        return String.format(Locale.getDefault(), quote_urls[i]);
    }
}
