/*
 * Copyright (C)2009 - SSHJ Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.schmizz.sshj.transport.mac;

import org.spongycastle.util.encoders.Hex;
import org.junit.Test;

import java.nio.charset.Charset;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HMACSHA196Test {
    private static final Charset CHARSET = Charset.forName("US-ASCII");
    private static final byte[] PLAIN_TEXT = "Hello World".getBytes(CHARSET);
    private static final String EXPECTED_HMAC = "24ddeed57ad91465c5b59dce";

    @Test
    public void testUpdateWithDoFinal() {
        HMACSHA196 hmac = initHmac();
        hmac.update(PLAIN_TEXT);
        assertThat(Hex.toHexString(hmac.doFinal()), is(EXPECTED_HMAC));
    }

    @Test
    public void testDoFinalWithInput() {
        HMACSHA196 hmac = initHmac();
        assertThat(Hex.toHexString(hmac.doFinal(PLAIN_TEXT)), is(EXPECTED_HMAC));
    }

    @Test
    public void testUpdateWithDoFinalWithResultBuffer() {
        HMACSHA196 hmac = initHmac();
        byte[] resultBuf = new byte[12];
        hmac.update(PLAIN_TEXT);
        hmac.doFinal(resultBuf, 0);
        assertThat(Hex.toHexString(resultBuf), is(EXPECTED_HMAC));
    }

    private HMACSHA196 initHmac() {
        HMACSHA196 hmac = new HMACSHA196();
        hmac.init("et1Quo5ooCie6theel8i".getBytes(CHARSET));
        return hmac;
    }
}
