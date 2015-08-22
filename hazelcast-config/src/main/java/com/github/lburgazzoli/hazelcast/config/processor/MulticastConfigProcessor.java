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

package com.github.lburgazzoli.hazelcast.config.processor;

import com.github.lburgazzoli.hazelcast.config.HzConfig;
import com.github.lburgazzoli.hazelcast.config.HzConfigProcessor;
import com.hazelcast.config.MulticastConfig;

import java.util.List;

public class MulticastConfigProcessor implements HzConfigProcessor<MulticastConfig> {
    public static final MulticastConfigProcessor INSTANCE = new MulticastConfigProcessor();

    @SuppressWarnings("unchecked")
    @Override
    public MulticastConfig apply(MulticastConfig config, String key, Object value) {
        switch(key) {
            case "trusted-interfaces":
                ((List<String>)value).forEach(config::addTrustedInterface);
                break;
            default:
                HzConfig.setPropertyValue(config, key, value);
                break;
        }

        return config;
    }
}
