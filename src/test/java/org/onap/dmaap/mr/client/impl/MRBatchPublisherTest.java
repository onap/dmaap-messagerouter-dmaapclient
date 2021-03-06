/*******************************************************************************
 *  ============LICENSE_START=======================================================
 *  org.onap.dmaap
 *  ================================================================================
 *  Copyright © 2017 AT&T Intellectual Property. All rights reserved.
 *  ================================================================================
 *  Modifications Copyright © 2021 Orange.
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

package org.onap.dmaap.mr.client.impl;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class MRBatchPublisherTest {

    private Collection<String> hosts = new HashSet<>(Arrays.asList("/test"));
    private MRBatchPublisher mrBatchPublisher = new MRBatchPublisher(hosts, "topic", 2, 20, true);


    @Before
    public void setup() {


    }

    @Test(expected = Test.None.class /* no exception expected */)
    public void testSend() throws IOException {
        mrBatchPublisher.send("testmessage");
    }

    @Test(expected = Test.None.class /* no exception expected */)
    public void testClose() throws IOException {
        mrBatchPublisher.close();
    }

}
