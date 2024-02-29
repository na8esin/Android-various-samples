package com.example.various.exoplayer2

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.various.databinding.FragmentExoPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ext.ima.ImaAdsLoader
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.MediaSourceFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * https://groups.google.com/g/google-admob-ads-sdk/c/JcH4n51vG3w
 * のせいで、api34で起動しない
 */
class ExoPlayerFragment : Fragment() {
    private lateinit var binding: FragmentExoPlayerBinding

    private var adsLoader: ImaAdsLoader? = null
    private var mExoPlayer: ExoPlayer? = null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExoPlayerBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        initializePlayer(
            Uri.parse("https://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8"),
            Uri.parse("https://pubads.g.doubleclick.net/gampad/ads?iu=/21775744923/external/single_ad_samples&sz=640x480&cust_params=sample_ct%3Dlinear&ciu_szs=300x250%2C728x90&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator=")
        )
    }

    private fun initializePlayer(uri: Uri, adUri: Uri) {
        Log.d(TAG, "initializePlayer")

        adsLoader = ImaAdsLoader.Builder(requireActivity()).build()

        val playerView = binding.playerView

        val mediaSourceFactory: MediaSourceFactory =
            DefaultMediaSourceFactory(requireActivity())
                .setAdsLoaderProvider { adsLoader }
                .setAdViewProvider(playerView)

        mExoPlayer =
            ExoPlayer.Builder(requireContext()).setMediaSourceFactory(mediaSourceFactory)
                .build()

        adsLoader?.setPlayer(mExoPlayer)

        val mediaItem: MediaItem = MediaItem.Builder()
            .setUri(uri)
            .setAdsConfiguration(MediaItem.AdsConfiguration.Builder(adUri).build())
            .build()

        mExoPlayer?.setMediaItem(mediaItem)
        mExoPlayer?.prepare()
        mExoPlayer?.playWhenReady = true
    }

    companion object {
        const val TAG = "ExoPlayerFragment"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExoPlayerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ExoPlayerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}