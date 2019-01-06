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

public class Parser
{
    public static void main( String[] args ) throws Exception
    {
        String paragraphStart = "<p>";
        String paragraphEnd   = "</p>";

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
        int index = 0;
        while ( contents.indexOf( paragraphStart, index ) >= 0 )
        {
            if ( index > 0 ) System.out.println();
            int start = contents.indexOf( paragraphStart, index );
            int end   = contents.indexOf( paragraphEnd,   index ) + paragraphEnd.length();
            String paragraph = contents.substring( start, end );
            System.out.println( "Start     : " + start     );
            System.out.println( "End       : " + end       );
            System.out.println( "Paragraph : " + paragraph );
            index = end;
        }
    }
}
