package com.him.sama.spotifycompose.common.ui.preview

import androidx.compose.ui.tooling.preview.Preview

object Pixel7 {
    const val name = "Pixel7"
    const val spec = "spec:id=reference_phone,shape=Normal,width=411,height=819,unit=dp,dpi=560"
}

object Television {
    const val name = "Television"
    const val spec = "spec:id=reference_tablet,shape=Normal,width=960,height=540,unit=dp,dpi=320"
}

object PixelTablet {
    const val name = "PixelTablet"
    const val spec = "spec:id=reference_tablet,shape=Normal,width=1280,height=728,unit=dp,dpi=320"
}

object Automotive {
    const val name = "Automotive"
    const val spec = "spec:id=reference_tablet,shape=Normal,width=1440,height=628,unit=dp,dpi=120"
}

@Preview(group = Pixel7.name, device = Pixel7.spec, showBackground = true)
annotation class MobilePreview

@Preview(group = Television.name, device = Television.spec, showBackground = true)
annotation class TelevisionPreview

@Preview(group = PixelTablet.name, device = PixelTablet.spec, showBackground = true)
annotation class TabletPreview

@Preview(group = Automotive.name, device = Automotive.spec, showBackground = true)
annotation class AutomotivePreview
