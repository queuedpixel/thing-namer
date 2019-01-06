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
import java.util.*;

public class Parser
{
    public static void main( String[] args ) throws Exception
    {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ));
        String line = reader.readLine();
        while ( line != null )
        {
            if ( builder.length() > 0 ) builder.append( " " );
            builder.append( line );
            line = reader.readLine();
        }

        String contents = builder.toString();
        Collection< String > paragraphs = Parser.getParagraphs( contents );
        Map< String, Set< String >> entries = new LinkedHashMap<>();
        for ( String paragraph : paragraphs )
        {
            String entry        = Parser.getEntry(        paragraph );
            String partOfSpeech = Parser.getPartOfSpeech( paragraph );

            // ignore null entries
            if (( entry == null ) || ( partOfSpeech == null )) continue;

            // ignore words that aren't composed of only letters
            if ( !entry.matches( "[a-zA-Z][a-z]*" )) continue;

            // convert the entry to lower case
            entry = entry.toLowerCase();

            // look for the parts of speech we are interested in
            partOfSpeech = partOfSpeech.trim();
            switch ( partOfSpeech )
            {
                case "a." : partOfSpeech = "Adjective"; break;
                case "n." : partOfSpeech = "Noun";      break;
                default   : continue;
            }

            Set< String > partsOfSpeech;

            if ( entries.containsKey( entry ))
            {
                // add the part of speech to the existing entry
                partsOfSpeech = entries.get( entry );
            }
            else
            {
                // add a new entry
                partsOfSpeech = new LinkedHashSet<>();
                entries.put( entry, partsOfSpeech );
            }

            partsOfSpeech.add( partOfSpeech );
        }

        // print out the entries
        boolean first = true;
        for ( String entry : entries.keySet() )
        {
            if ( first )
            {
                first = false;
            }
            else
            {
                System.out.println();
            }

            System.out.println( "Entry          : " + entry );

            for ( String partOfSpeech : entries.get( entry ))
            {
                System.out.println( "Part of Speech : " + partOfSpeech );
            }
        }
    }

    private static Collection< String > getParagraphs( String contents )
    {
        String paragraphStart = "<p>";
        String paragraphEnd   = "</p>";

        LinkedList< String > strings = new LinkedList<>();
        int index = 0;
        while ( contents.indexOf( paragraphStart, index ) >= 0 )
        {
            int start = contents.indexOf( paragraphStart, index );
            int end   = contents.indexOf( paragraphEnd,   index );

            // ignore paragraphs with no start tag
            if ( end < start )
            {
                index = start;
                continue;
            }

            end += paragraphEnd.length();
            strings.add( contents.substring( start, end ));
            index = end;
        }

        return strings;
    }

    private static String getEntry( String paragraph )
    {
        return Parser.getTag( paragraph, "<ent>", "<" );
    }

    private static String getPartOfSpeech( String paragraph )
    {
        return Parser.getTag( paragraph, "<pos>", "<" );
    }

    private static String getTag( String content, String tagStart, String tagEnd )
    {
        int start = content.indexOf( tagStart );
        int end   = content.indexOf( tagEnd, start + tagStart.length() );
        if ( start < 0 ) return null;
        start += tagStart.length();
        return content.substring( start, end );
    }
}
