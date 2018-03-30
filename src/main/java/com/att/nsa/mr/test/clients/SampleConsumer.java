/*******************************************************************************
 *  ============LICENSE_START=======================================================
 *  org.onap.dmaap
 *  ================================================================================
 *  Copyright © 2017 AT&T Intellectual Property. All rights reserved.
 *  ================================================================================
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *        http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  ============LICENSE_END=========================================================
 *
 *  ECOMP is a trademark and service mark of AT&T Intellectual Property.
 *  
 *******************************************************************************/
package com.att.nsa.mr.test.clients;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.att.nsa.mr.client.MRClientFactory;
import com.att.nsa.mr.client.MRConsumer;

public class SampleConsumer {
    private SampleConsumer() {
    }
	public static void main ( String[] args )
	{
		final Logger log = LoggerFactory.getLogger(SampleConsumer.class);

		
		log.info("Sample Consumer Class executing");
		final String topic = "com.att.app.dmaap.mr.testingTopic";
		final String url = ( args.length > 1 ? args[1] : "localhost:8181" );
		final String group = ( args.length > 2 ? args[2] :"grp" );
		/*final String id = ( args.length > 3 ? args[3] : "0" );*/
		final String id = ( args.length > 3 ? args[3] : "1" );

		long count = 0;
		long nextReport = 5000;

		final long startMs = System.currentTimeMillis ();

		final LinkedList<String> urlList = new LinkedList<String> ();
		for ( String u : url.split ( "," ) )
		{
			urlList.add ( u );
		}

		final MRConsumer cc = MRClientFactory.createConsumer ( urlList, topic, group, id, 10*1000, 1000, null, "CG0TXc2Aa3v8LfBk", "pj2rhxJWKP23pgy8ahMnjH88" );
		try
		{
			while ( true )
			{
				for ( String msg : cc.fetch () )
				{
					log.info ( "" + (++count) + ": " + msg );
				}
	
				if ( count > nextReport )
				{
					nextReport += 5000;
	
					final long endMs = System.currentTimeMillis ();
					final long elapsedMs = endMs - startMs;
					final double elapsedSec = elapsedMs / 1000.0;
					final double eps = count / elapsedSec;
					log.info ( "Consumed " + count + " in " + elapsedSec + "; " + eps + " eps" );
				}
				log.info ( "" + (++count) + ": consumed message" );
			}
		}
		catch ( Exception x )
		{
                    log.error( x.getClass().getName () + ": " + x.getMessage () );
                    throw new IllegalArgumentException(x);
		}
	}
}
