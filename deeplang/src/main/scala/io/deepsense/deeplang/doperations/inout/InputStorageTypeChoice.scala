/**
 * Copyright 2015, deepsense.io
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

package io.deepsense.deeplang.doperations.inout

import io.deepsense.deeplang.params.choice.{Choice, ChoiceParam}
import io.deepsense.deeplang.params.library.LoadFromLibraryParam
import io.deepsense.deeplang.params.{Param, StorageType, StringParam}

sealed trait InputStorageTypeChoice extends Choice {
  import InputStorageTypeChoice._

  override val choiceOrder: List[Class[_ <: InputStorageTypeChoice]] = List(
    classOf[File],
    classOf[Jdbc],
    classOf[GoogleSheet]
  )
}

object InputStorageTypeChoice {

  class File extends InputStorageTypeChoice {

    override val name: String = StorageType.FILE.toString

    val sourceFile = LoadFromLibraryParam(
      name = "source",
      description = "Path to the DataFrame file.")

    def getSourceFile(): String = $(sourceFile)
    def setSourceFile(value: String): this.type = set(sourceFile, value)

    val fileFormat = ChoiceParam[InputFileFormatChoice](
      name = "format",
      description = "Format of the input file.")
    setDefault(fileFormat, new InputFileFormatChoice.Csv())

    def getFileFormat(): InputFileFormatChoice = $(fileFormat)
    def setFileFormat(value: InputFileFormatChoice): this.type = set(fileFormat, value)

    override def params = declareParams(sourceFile, fileFormat)
  }

  class Jdbc extends InputStorageTypeChoice with JdbcParameters {

    override val name: String = StorageType.JDBC.toString
    override def params: Array[Param[_]] =
      declareParams(jdbcUrl, jdbcDriverClassName, jdbcTableName)
  }

  class GoogleSheet extends InputStorageTypeChoice with NamesIncludedParam with HasShouldConvertToBooleanParam {

    override val name: String = "Google Sheet"
    override lazy val params = declareParams(
      googleSheetId, serviceAccountCredentials, namesIncluded, shouldConvertToBoolean
    )

    val googleSheetId = StringParam(
      name = "Google Sheet Id",
      description = "Id of google sheet")
    setDefault(googleSheetId, "")

    def getGoogleSheetId(): String = $(googleSheetId)
    def setGoogleSheetId(value: String): this.type = set(googleSheetId, value)

    val serviceAccountCredentials = StringParam(
      name = "Google Service Account credentials JSON",
      description = "Json file representing google service account credentials to be used for reading " +
        "google sheet. For more details read documentation #TODO_LINK")
    setDefault(serviceAccountCredentials, "")

    def getGoogleServiceAccountCredentials(): String = $(serviceAccountCredentials)
    def setGoogleServiceAccountCredentials(value: String): this.type = set(serviceAccountCredentials, value)

  }

}
