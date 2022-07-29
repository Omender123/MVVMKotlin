package com.example.mvvmtask.Fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtask.Adapter.CoinListAdapter
import com.example.mvvmtask.ViewModel.CoinListViewModel
import com.example.mvvmtask.databinding.FragmentHomeBinding
import com.example.mvvmtask.utils.EqualSpacingItemDecoration
import com.example.mymftcustomer.IMainActivity


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val getviewModel: CoinListViewModel by viewModels()
    private var mIMainActivity: IMainActivity? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getviewModel.getcoinList()
        getCoinListData()
    }

    private fun getCoinListData() {
        getviewModel.getProgressObserver.observe(viewLifecycleOwner) {
            mIMainActivity?.showProgress(it)
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }


        getviewModel.getCoinListObserver.observe(viewLifecycleOwner) {
            if (it.data.list!=null && it.data.list.size>0){
                val mLayoutManager1: RecyclerView.LayoutManager =
                    GridLayoutManager(context,3, RecyclerView.VERTICAL, false)

                binding.recyclerView.setLayoutManager(mLayoutManager1)
                binding.recyclerView.setItemAnimator(DefaultItemAnimator())
                binding.recyclerView.addItemDecoration(
                    EqualSpacingItemDecoration(
                        10,
                        EqualSpacingItemDecoration.VERTICAL
                    )
                )
                var addToCartDetailAdapter =
                    CoinListAdapter(requireContext(), it.data.list);
                binding.recyclerView.setAdapter(addToCartDetailAdapter)
            }

        }


        getviewModel.getMessageObserver.observe(viewLifecycleOwner) {
            Log.d(
                ContentValues.TAG,
                "cartDataObserver: $it"
            )
        }
    }

}