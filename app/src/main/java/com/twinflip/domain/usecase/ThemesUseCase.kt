package com.twinflip.domain.usecase

import com.twinflip.domain.model.Theme
import com.twinflip.domain.repository.ThemeRepository

class ThemesUseCase(
    private val themeRepository: ThemeRepository
) {
    operator fun invoke(): List<Theme> {
      return  themeRepository.getThemes()
    }


}