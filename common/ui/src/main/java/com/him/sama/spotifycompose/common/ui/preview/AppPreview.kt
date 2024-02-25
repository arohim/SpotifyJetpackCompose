package com.him.sama.spotifycompose.common.ui.preview

import androidx.compose.ui.tooling.preview.Preview

object Pixel7 {
    const val name = "Pixel7"
    const val spec = "spec:id=reference_phone,shape=Normal,width=411,height=914,unit=dp,dpi=420"
}

object PixelTablet {
    const val name = "PixelTablet"
    const val spec = "spec:id=reference_tablet,shape=Normal,width=1280,height=800,unit=dp,dpi=320"
}

object Automotive {
    const val name = "PixelTablet"
    const val spec = "spec:id=reference_tablet,shape=Normal,width=1280,height=800,unit=dp,dpi=320"
}

object Desktop {
    const val name = "PixelTablet"
    const val spec = "spec:id=reference_tablet,shape=Normal,width=1280,height=800,unit=dp,dpi=320"
}

@Preview(group = Pixel7.name, device = Pixel7.spec, showBackground = true)
annotation class MobilePreview

@Preview(group = PixelTablet.name, device = PixelTablet.spec, showBackground = true)
annotation class TabletPreview

@Preview(group = PixelTablet.name, device = PixelTablet.spec, showBackground = true)
annotation class AutomotivePreview

@Preview(group = PixelTablet.name, device = PixelTablet.spec, showBackground = true)
annotation class DesktopPreview
