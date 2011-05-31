/*
 * Copyright 2011 Mozilla Foundation
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mozilla.bagheera.elasticsearch;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;

public class NodeClientSingleton {

	private static NodeClientSingleton INSTANCE;
	
	private final Node node;
	private final Client client;
	
	private NodeClientSingleton() {
		this.node = nodeBuilder().loadConfigSettings(true).node().start();
		this.client = node.client();
	}
	
	public static NodeClientSingleton getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new NodeClientSingleton();
		}
		
		return INSTANCE;
	}
	
	public void close() {
		if (client != null) {
			client.close();
		}
		
		if (node != null) {
			node.close();
		}
	}
	
	public Node getNode() {
		return this.node;
	}
	
	public Client getClient() {
		return this.client;
	}
	
}
