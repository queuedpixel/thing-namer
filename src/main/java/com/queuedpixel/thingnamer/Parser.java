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
import java.util.Collection;
import java.util.LinkedList;

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
        for ( String paragraph : paragraphs )
        {
            System.out.println( "Entry: " + Parser.getEntry( paragraph ));
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
            int end   = contents.indexOf( paragraphEnd,   index ) + paragraphEnd.length();
            strings.add( contents.substring( start, end ));
            index = end;
        }

        return strings;
    }

    private static String getEntry( String paragraph )
    {
        String entryStart = "<ent>";
        String entryEnd   = "</ent>";

        int start = paragraph.indexOf( entryStart );
        int end   = paragraph.indexOf( entryEnd   );
        if ( start < 0 ) return null;
        start += entryStart.length();
        return paragraph.substring( start, end );
    }
}
