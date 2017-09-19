/**
 * Copyright 2017 deepsense.ai (CodiLime, Inc)
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

'use strict';

// Libs
import angular from 'angular';

// App
import apiModule from './api/api.module';
import datasourcesModule from './datasources/datasources.module';
import directivesModule from './directives/directives.module';
import filtersModule from './filters/filters.module';
import helpersModule from './helpers/helpers.module';
import schedulesModule from './schedules/schedules.module';


export const CommonModule = angular
  .module('common', [
    apiModule,
    datasourcesModule,
    directivesModule,
    filtersModule,
    helpersModule,
    schedulesModule
  ])
  .name;