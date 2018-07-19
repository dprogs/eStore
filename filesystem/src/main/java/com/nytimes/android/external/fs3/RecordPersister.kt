package com.nytimes.android.external.fs3

import com.nytimes.android.external.fs3.filesystem.FileSystem
import com.nytimes.android.external.store3.base.RecordProvider
import com.nytimes.android.external.store3.base.RecordState
import com.nytimes.android.external.store3.base.impl.BarCode

import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RecordPersister @Inject
constructor(fileSystem: FileSystem,
            private val expirationDuration: Long,
            private val expirationUnit: TimeUnit) : SourcePersister(fileSystem), RecordProvider<BarCode> {

    override fun getRecordState(barCode: BarCode): RecordState = sourceFileReader.getRecordState(barCode, expirationUnit, expirationDuration)

    companion object {

        fun create(fileSystem: FileSystem,
                   expirationDuration: Long,
                   expirationUnit: TimeUnit): RecordPersister = RecordPersister(fileSystem, expirationDuration, expirationUnit)
    }
}