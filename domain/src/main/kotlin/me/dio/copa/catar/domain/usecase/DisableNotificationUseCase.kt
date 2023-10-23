import me.dio.copa.catar.data.source.MatchesDataSource

class DisableNotificationUseCase(
    private val localDataSource: MatchesDataSource.Local
) {
    suspend fun disableNotificationForMatch(matchId: String) {
        localDataSource.disableNotificationFor(matchId)
    }
}
