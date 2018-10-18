package com.perfectcomputersolutions.pos.utility

import com.perfectcomputersolutions.pos.exception.CaughtException

import javax.servlet.ReadListener
import javax.servlet.ServletInputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper

class MultiReadHttpServletRequest extends HttpServletRequestWrapper {

    // https://howtodoinjava.com/servlets/httpservletrequestwrapper-example-read-request-body/
    // https://gist.github.com/calo81/2071634
    // http://www.myjavarecipes.com/tag/how-to-read-request-twice/

    String body = ""

    MultiReadHttpServletRequest(HttpServletRequest request) throws IOException {

        super(request)

        def reader = request.getReader()
        def sb     = new StringBuilder()

        try {

            // TODO - Ready by 128-byte byte arrays instead of by line in case lines are really long

            // TODO - Throwing IOException with "Stream closed" in testing!!

            String line

            while ((line = reader.readLine()) != null)
                sb.append(line)

            body = sb.toString()

        } catch (IOException ex1) {

            throw new CaughtException("Failed to read HTTP request body", ex1)

        } finally {

            if (reader != null) {

                try {

                    reader.close()

                } catch (IOException ex2) {

                    throw new CaughtException("Failed to finish reading HTTP request body", ex2)
                }
            }

        }

    }

    @Override
    ServletInputStream getInputStream() throws IOException {

        def final stream = new ByteArrayInputStream(body.bytes)

        return new ServletInputStream() {

            int read() throws IOException {

                return stream.read()
            }

            @Override
            boolean isFinished() {

                return stream.available() == 0
            }

            @Override
            boolean isReady() {

                return true
            }

            @Override
            void setReadListener(ReadListener listener) {

                throw new RuntimeException("Not implemented")
            }
        }
    }

    @Override
    BufferedReader getReader() throws IOException {

        return new BufferedReader(new InputStreamReader(this.inputStream))
    }
}