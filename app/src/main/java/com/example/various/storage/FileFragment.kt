package com.example.various.storage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.various.R
import com.example.various.databinding.FragmentFileBinding
import kotlinx.coroutines.launch
import java.io.File

class FileFragment : Fragment() {

    companion object {
        fun newInstance() = FileFragment()
    }
    private lateinit var binding: FragmentFileBinding
    private lateinit var viewModel: FileViewModel

    /**
     * https://developer.android.com/training/data-storage?hl=ja
     * > DataStore offers a more modern way of storing local data. You should use DataStore instead of SharedPreferences. Read the DataStore guide for more information.
     *
     * SharedPreferencesが非推奨なら、File使えばよくないですか？と言われた時に困ったので調査。
     * 多分、そもそもエンジニア歴自体が浅い人はそんなこと言わないと思うが、
     * バックエンド系のエンジニアの人が、いきなりアプリを書くとなった時に言いそう
     *
     * 正直これだけで動いちゃうから、手取り速さはNo.1。
     *
     * 問題点
     * - 複数のスレッドからの書き込み
     * - 型
     * - マイグレーション
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFileBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(FileViewModel::class.java)

        fileWrite()
        fileRead()
        return binding.root
    }

    private fun fileWrite() {
        val file = File(requireContext().filesDir, "test.txt")
        file.writeText("test")
    }

    private fun fileRead() {
        val file = File(requireContext().filesDir, "test.txt")
        binding.textView.text = file.readText()
    }
}