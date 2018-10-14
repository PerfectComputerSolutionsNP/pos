package com.perfectcomputersolutions.pos.utility

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

        BufferedReader bufferedReader = request.getReader()

        def sb = new StringBuilder()

        try {

            String line

            while ((line = bufferedReader.readLine()) != null)
                sb.append(line)

            body = sb.toString()

        } catch (IOException ex1) {

            throw ex1

        } finally {

            if (bufferedReader != null) {

                try {

                    bufferedReader.close()

                } catch (IOException ex2) {

                    throw ex2
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