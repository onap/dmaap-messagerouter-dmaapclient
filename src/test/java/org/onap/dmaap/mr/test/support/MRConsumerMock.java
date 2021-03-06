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

package org.onap.dmaap.mr.test.support;

import org.onap.dmaap.mr.client.MRConsumer;
import org.onap.dmaap.mr.client.response.MRConsumerResponse;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * A helper for unit testing systems that use a MRConsumer. When setting
 * up your test, inject an instance into MRClientFactory to have it return
 * the mock client.
 *
 * @author author
 */
public class MRConsumerMock implements MRConsumer {
    public class Entry {
        public Entry(long waitMs, int statusCode, List<String> msgs) {
            fWaitMs = waitMs;
            fStatusCode = statusCode;
            fStatusMsg = null;
            fMsgs = new LinkedList<>(msgs);
        }

        public Entry(long waitMs, int statusCode, String statusMsg) {
            fWaitMs = waitMs;
            fStatusCode = statusCode;
            fStatusMsg = statusMsg;
            fMsgs = null;
        }

        public LinkedList<String> run() throws IOException {
            try {
                Thread.sleep(fWaitMs);
                if (fStatusCode >= 200 && fStatusCode <= 299) {
                    return fMsgs;
                }
                throw new IOException("" + fStatusCode + " " + fStatusMsg);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IOException(e);
            }
        }

        private final long fWaitMs;
        private final int fStatusCode;
        private final String fStatusMsg;
        private final LinkedList<String> fMsgs;
    }

    public MRConsumerMock() {
        fReplies = new LinkedList<>();
    }

    @Override
    public void close() {
    }

    @Override
    public void setApiCredentials(String apiKey, String apiSecret) {
    }

    @Override
    public void clearApiCredentials() {
    }

    public synchronized void add(Entry e) {
        fReplies.add(e);
    }

    public void addImmediateMsg(String msg) {
        addDelayedMsg(0, msg);
    }

    public void addDelayedMsg(long delay, String msg) {
        final LinkedList<String> list = new LinkedList<>();
        list.add(msg);
        add(new Entry(delay, 200, list));
    }

    public void addImmediateMsgGroup(List<String> msgs) {
        addDelayedMsgGroup(0, msgs);
    }

    public void addDelayedMsgGroup(long delay, List<String> msgs) {
        final LinkedList<String> list = new LinkedList<>(msgs);
        add(new Entry(delay, 200, list));
    }

    public void addImmediateError(int statusCode, String statusText) {
        add(new Entry(0, statusCode, statusText));
    }

    @Override
    public Iterable<String> fetch() throws IOException {
        return fetch(-1, -1);
    }

    @Override
    public Iterable<String> fetch(int timeoutMs, int limit) throws IOException {
        return fReplies.size() > 0 ? fReplies.removeFirst().run() : new LinkedList<String>();
    }

    @Override
    public void logTo(Logger log) {
    }

    private final LinkedList<Entry> fReplies;

    @Override
    public MRConsumerResponse fetchWithReturnConsumerResponse() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MRConsumerResponse fetchWithReturnConsumerResponse(int timeoutMs,
                                                              int limit) {
        // TODO Auto-generated method stub
        return null;
    }
}
