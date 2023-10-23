package me.dio.copa.catar.features

import me.dio.copa.catar.notification.scheduler.extensions.NotificationMatcherWorker
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dagger.hilt.android.AndroidEntryPoint
import me.dio.copa.catar.domain.model.MatchDomain
import me.dio.copa.catar.extensions.observe
import me.dio.copa.catar.notification.scheduler.extensions.NotificationMatcherWorker
import me.dio.copa.catar.ui.theme.Copa2022Theme
import me.dio.copa.catar.remote.model.Match

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeMainViewModelActions()
        setContent {
            Copa2022Theme {
                val state by mainViewModel.state.collectAsState()
                MainScreen(matches = state.matches, mainViewModel::toggleNotification)
            }
        }
    }

    private fun observeMainViewModelActions() {
        mainViewModel.action.observe(this) { action ->
            when (action) {
                is MainUiAction.MatchesNotFound -> handleMatchesNotFound()
                MainUiAction.Unexpected -> handleUnexpectedAction()
                is MainUiAction.DisableNotification -> handleDisableNotification(action.match)
                is MainUiAction.EnableNotification -> handleEnableNotification(action.match)
            }
        }
    }

    private fun handleMatchesNotFound() {
        // Lógica para lidar com a ação MainUiAction.MatchesNotFound
        val errorMessage = "Nenhuma partida encontrada."
        // Você pode exibir esta mensagem na interface do usuário, como um Toast, um Snackbar, ou uma caixa de diálogo.
        showToast(errorMessage)
    }

    private fun showToast(message: String) {
        // Implemente aqui a lógica para mostrar a mensagem de erro ao usuário, por exemplo, usando um Toast.
        // Substitua este comentário pela implementação real do Toast.
        // Por exemplo:
        // Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun handleUnexpectedAction() {
        // Lógica para lidar com a ação MainUiAction.Unexpected
        val errorMessage = "Ocorreu um erro inesperado."
        // Você pode registrar o erro em um sistema de logs ou notificar o usuário, conforme necessário.
        // Por exemplo, registrar em um sistema de logs:
        Log.e("MainActivity", errorMessage)
        // Ou notificar o usuário:
        // showToast(errorMessage)
    }

    private fun handleDisableNotification(match: MatchDomain) {
        // Lógica para lidar com a ação MainUiAction.DisableNotification
        // TODO: Implemente aqui a ação desejada
        NotificationMatcherWorker.cancel(applicationContext, match)
    }

    private fun handleEnableNotification(match: Match) {
        // Lógica para lidar com a ação MainUiAction.EnableNotification
        // TODO: Implemente aqui a ação desejada
        NotificationMatcherWorker.start(applicationContext, match)
    }
}