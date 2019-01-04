/*

thing-namer : Combine Words Together to Form a Random Name

Copyright (c) 2019 Queued Pixel <git@queuedpixel.com>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

*/

package com.queuedpixel.thingnamer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Main
{
    public static void main( String[] args ) throws Exception
    {
        int nameCount = 10;
        int wordCount = 2;

        ArrayList< String > wordList = new ArrayList<>();
        BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ));
        String line = reader.readLine();
        while ( line != null )
        {
            wordList.add( line );
            line = reader.readLine();
        }

        System.out.println( "Word List:" );
        for ( String word : wordList )
        {
            System.out.println( "- " + word );
        }

        Random random = new Random();
        System.out.println( "Random Names:" );
        for ( int i = 0; i < nameCount; i++ )
        {
            String name = "";
            for ( int j = 0; j < wordCount; j++ )
            {
                if ( name.length() > 0 ) name += "-";
                int index = random.nextInt( wordList.size() );
                name += wordList.get( index );
            }

            System.out.println( "- " + name );
        }
    }
}
