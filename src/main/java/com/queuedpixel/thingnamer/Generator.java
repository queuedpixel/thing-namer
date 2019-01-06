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
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator
{
    public static void main( String[] args ) throws Exception
    {
        int minSize   = 4;
        int maxSize   = 8;
        int nameCount = 50;

        List< String > adjectives = Generator.readWordList( "adjectives.txt", minSize, maxSize );
        List< String > nouns      = Generator.readWordList( "nouns.txt",      minSize, maxSize );

        Random random = new Random();
        for ( int i = 0; i < nameCount; i++ )
        {
            int adjectiveIndex = random.nextInt( adjectives.size() );
            int nounIndex      = random.nextInt( nouns.size()      );
            String name = adjectives.get( adjectiveIndex ) + "-" + nouns.get( nounIndex );
            System.out.println( name );
        }
    }

    private static List< String> readWordList( String fileName, int minSize, int maxSize ) throws Exception
    {
        List< String > words = new ArrayList<>();

        try ( BufferedReader reader = new BufferedReader( new FileReader( fileName )))
        {
            String line = reader.readLine();
            while ( line != null )
            {
                // only add words within our size limits
                if (( line.length() >= minSize ) && ( line.length() <= maxSize )) words.add( line );
                line = reader.readLine();
            }
        }

        return words;
    }
}
