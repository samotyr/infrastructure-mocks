/*
Copyright 2016 Peter Bilstein

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package soapmocks.generic.proxy;

import java.io.File;
import java.io.IOException;

import soapmocks.Constants;
import soapmocks.generic.logging.SoapMocksLogFactory;
import soapmocks.generic.logging.SoapMocksLogger;
import soapmocks.io.FileUtils;

final class ProxyRecordHandler {

    private static final SoapMocksLogger LOG = SoapMocksLogFactory.create(ProxyRecordHandler.class);
    
    void handleProxyRecord(ProxyResult proxyResult) throws IOException {
	if(ProxyDelegator.hasServiceIdentifier()) {
	    ProxyServiceIdentifier serviceIdentifier = ProxyDelegator.getServiceIdentifier();
	    File file = new File(getProxyTraceDir()+serviceIdentifier.generateFilename());
	    LOG.out("Proxy recorded to " + file.getName());
	    FileUtils.writeByteArrayToFile(file, proxyResult.body);
	}
    }
    
    private String getProxyTraceDir() {
	String proxyRecordDir = System.getProperty(Constants.SOAPMOCKS_PROXYRECORD_DIR_SYSTEM_PROP);
	if(proxyRecordDir!=null && !proxyRecordDir.isEmpty()) {
	    return proxyRecordDir;
	}
	return "target/proxyrecord/";
    }
}