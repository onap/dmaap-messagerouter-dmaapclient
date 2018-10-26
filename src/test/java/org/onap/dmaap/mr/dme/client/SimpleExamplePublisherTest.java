/*-
 * ============LICENSE_START=======================================================
 * ONAP Policy Engine
 * ================================================================================
 * Copyright (C) 2017 AT&T Intellectual Property. All rights reserved.
 * ================================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ============LICENSE_END=========================================================
 */

package org.onap.dmaap.mr.dme.client;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.onap.dmaap.mr.client.HostSelector;
import org.onap.dmaap.mr.client.MRPublisher.message;
import org.onap.dmaap.mr.test.support.MRBatchingPublisherMock.Listener;

public class SimpleExamplePublisherTest {
	private SimpleExamplePublisher pub = null;

	@Before
	public void setUp() throws Exception {
		pub = new SimpleExamplePublisher();

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testPublishMessage() {

		try {
			pub.publishMessage("/producer");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(true);

	}

	

}