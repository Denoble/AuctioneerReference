package com.gevcorst.auctioneerreference.model.services

import com.google.firebase.ktx.BuildConfig
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import javax.inject.Inject
import com.gevcorst.auctioneerreference.R.xml as AppConfig


interface ConfigurationService {
    suspend fun getConfiguration():Boolean
    val isTaskEditButtonShowing:Boolean

}
class ConfigurationServiceImpl @Inject constructor():ConfigurationService  {
    private val remoteConfig
        get() = Firebase.remoteConfig
    init {
        if(BuildConfig.DEBUG){
            val configSettings = remoteConfigSettings { minimumFetchIntervalInSeconds = 0 }
        }
        remoteConfig.setDefaultsAsync(AppConfig.remote_config_defaults)
    }
    override suspend fun getConfiguration(): Boolean = true

    override val isTaskEditButtonShowing: Boolean
        get() = true

    companion object {
        private const val SHOW_TASK_EDIT_BUTTON_KEY = "show_task_edit_button"
        private const val FETCH_CONFIG_TRACE = "fetchConfig"
    }
}