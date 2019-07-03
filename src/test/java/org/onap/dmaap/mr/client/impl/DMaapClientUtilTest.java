/*******************************************************************************
 *  ============LICENSE_START=======================================================
 *  org.onap.dmaap
 *  ================================================================================
 *  Copyright © 2018 IBM Intellectual Property. All rights reserved.
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class DMaapClientUtilTest {
    @Mock
    Response response;
    @Mock
    Builder builder;
    @Mock
    WebTarget target;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testGetTarget() {
    	WebTarget actual = DmaapClientUtil.getTarget("testpath");
        
        assertEquals("testpath", actual.getUri().getPath());
    }
    
    @Test
    public void testGetTargetWithParams() {
        WebTarget actual = DmaapClientUtil.getTarget("testpath", "testuser", "testpassword");
        
        assertEquals("testpath", actual.getUri().getPath());
    }
    
    @Test
    public void testGetResponsewtCambriaAuth() {
    	Mockito.when(target.request()).thenReturn(builder);
    	Mockito.when(builder.header("X-CambriaAuth", "testuser")).thenReturn(builder);
    	Mockito.when(builder.header("X-CambriaDate", "testpassword")).thenReturn(builder);
    	Mockito.when(builder.get()).thenReturn(response);
    	
        Response actual = DmaapClientUtil.getResponsewtCambriaAuth(target, "testuser", "testpassword");

        assertEquals(response, actual);
        verify(target).request();
        verify(builder, times(2)).header((String) any(), any());
    }

    

}
