package com.aasan.sample.socket

import com.aasan.sample.Extension
import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket

data class Status(
    var userId  :String,
    var status  :String,
    var token  :String
)


data class RoomData(
    var userId  :String,
    var strangerId  :String,
    var purpose  :String,
    var token  :String
)


object SocketManager {
    private var mSocket :Socket? = null
    private var UID :String = ""
    private var token :String = ""

    private val userStatus :Status = Status("","","")

    val gson:Gson = Gson()


    init {
        setupSocket()
    }

    private fun setupSocket(){
        try {
            mSocket  = IO.socket("")
        }
        catch (e:java.lang.Exception){
            e.printStackTrace()
        }

        mSocket?.connect()
    }

    fun getSocket():Socket? = mSocket

    fun setUserId(id:String){
        if(mSocket?.connected()!=true){
            if(mSocket?.connected()!=true){
                setupSocket()
            }
            UID = id
            userStatus.userId = id
        }
    }

    fun setToken(token: String){
        this.token = token
        userStatus.token = token
    }

    fun setOnline (online:Boolean){
        if(online){
            userStatus.status = "online"
        }
        else{
            userStatus.status = "offline"
        }

        mSocket?.emit("status",gson.toJson(userStatus))
    }
}