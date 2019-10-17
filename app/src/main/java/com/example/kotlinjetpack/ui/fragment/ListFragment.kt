package com.example.kotlinjetpack.ui.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation

import com.example.kotlinjetpack.R
import com.example.kotlinjetpack.adapter.RAdapter
import com.example.kotlinjetpack.bean.ObUser
import com.example.kotlinjetpack.databinding.FragmentBlankBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ListFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var btn: Button
    private lateinit var mBinding:FragmentBlankBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
         mBinding = DataBindingUtil.inflate<FragmentBlankBinding>(inflater,R.layout.fragment_blank,container,false)
        return  mBinding.getRoot();


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var users: MutableList<ObUser> = arrayListOf()
        //初始化三个数据
        for (i in 0..2) {
            users.add(
                    ObUser("https://cdn.cnn.com/cnnnext/dam/assets/191009234452-01-donald-trump-october-9-2019-medium-tease.jpg", 20 + i, i % 2, "Jim are friendsLiLy " +
                            "啦啦啦😝啦啦啦的8888888888 $i")
            )
        }
        btn = view.findViewById(R.id.btn_go2)
        var adapter  = RAdapter(this,users)
        mBinding.radapter =adapter
        btn.setOnClickListener{
            users.clear()
            //初始化三个数据
            for (i in 0..2) {
                users.add(
                        ObUser("https://tpc.googlesyndication.com/simgad/11396136500387258322", 20 + i, i % 2, "Jim are friendsLiLy " +
                                "啦啦啦😝啦啦啦的 $i")
                )
            }

           adapter.setData(users)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ListFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
