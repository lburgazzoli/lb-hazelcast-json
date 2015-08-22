/*
 * Copyright (c) 2015 Luca Burgazzoli
 *
 * https://github.com/lburgazzoli
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.lburgazzoli.hazelcast.config.processor.network;

import com.github.lburgazzoli.hazelcast.config.HzConfig;
import com.github.lburgazzoli.hazelcast.config.HzConfigProcessor;
import com.hazelcast.config.NetworkConfig;

import java.util.List;

import static com.github.lburgazzoli.hazelcast.config.HzConfig.forEachElementApply;

public class NetworkConfigProcessor implements HzConfigProcessor<NetworkConfig> {

    public static final NetworkConfigProcessor INSTANCE = new NetworkConfigProcessor();

    @SuppressWarnings("unchecked")
    @Override
    public NetworkConfig apply(NetworkConfig config, String key, Object value) {
        switch(key) {
            case "outbound-ports":
                ((List<String>) value).forEach(config::addOutboundPortDefinition);
                break;
            case "join":
                forEachElementApply(config::getJoin, value, JoinConfigProcessor.INSTANCE);
                break;
            case "interfaces":
                forEachElementApply(config::getInterfaces, value, InterfacesConfigProcessor.INSTANCE);
                break;
            default:
                HzConfig.setPropertyValue(config, key, value);
                break;
        }

        return config;
    }
}