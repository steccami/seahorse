/**
 * Copyright 2015 deepsense.ai (CodiLime, Inc)
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

package ai.deepsense.deeplang.doperables.spark.wrappers.params.common

import scala.language.reflectiveCalls

import org.apache.spark.ml

import ai.deepsense.deeplang.params.Params
import ai.deepsense.deeplang.params.wrappers.spark.SingleColumnCreatorParamWrapper

trait HasPredictionColumnCreatorParam extends Params {

  val predictionColumn =
    new SingleColumnCreatorParamWrapper[
      ml.param.Params { val predictionCol: ml.param.Param[String] }](
      name = "prediction column",
      description = Some("The prediction column created during model scoring."),
      sparkParamGetter = _.predictionCol)
  setDefault(predictionColumn, "prediction")
}
