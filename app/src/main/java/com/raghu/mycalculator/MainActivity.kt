package com.raghu.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.ViewFlipper

class MainActivity : AppCompatActivity() {

    private var res: TextView? = null
    private var clr: Button? = null
    var lastnumeric: Boolean = true
    var lastdot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "Built by RAGHU BALEMANE with ❤", Toast.LENGTH_LONG).show()
        res = findViewById(R.id.result)
        clr = findViewById(R.id.btclr)


    }

    fun ondigit(view: View) {
        res?.append((view as Button).text)
        lastnumeric = true
        lastdot = false

    }

    fun onclear(view: View) {
        res?.text = ""
    }

    fun ondots(view: View) {
        if (lastnumeric && !lastdot) {
            res?.append(".")
            lastnumeric = false
            lastdot = true
        }


    }
    private fun isoperatoradded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains('+')
                    || value.contains("-") || value.contains("*")

        }


    }
    fun onoperator(view: View) {
        res?.text?.let {
            if (lastnumeric && !isoperatoradded(it.toString()))
            {
                res?.append((view as Button).text)
                lastnumeric=false
                    lastdot=false
            }
        }
    }
    fun onequal(view: View)
    {
        if (lastnumeric)
        {
            var tvvalue=res?.text.toString()
            var prefix =""

            try {
                if(tvvalue.startsWith("-"))
                {
                    prefix="-"
                    tvvalue.substring(1)

                }
                if(tvvalue.contains("-"))
                {
                    val splitvalue= tvvalue.split("-")
                    var one= splitvalue[0]
                    var two= splitvalue[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix + one
                    }
                    res?.text= eliminatezero((one.toDouble() - two.toDouble()).toString())
                } else
                    if(tvvalue.contains("+"))
                    {
                        val splitvalue= tvvalue.split("+")
                        var one= splitvalue[0]
                        var two= splitvalue[1]
                        if(prefix.isNotEmpty())
                        {
                            one=prefix + one
                        }
                        res?.text= eliminatezero((one.toDouble() + two.toDouble()).toString())
                    }  else
                        if(tvvalue.contains("*"))
                        {
                            val splitvalue= tvvalue.split("*")
                            var one= splitvalue[0]
                            var two= splitvalue[1]
                            if(prefix.isNotEmpty())
                            {
                                one=prefix + one
                            }
                            res?.text= eliminatezero((one.toDouble() * two.toDouble()).toString())
                        } else
                            if(tvvalue.contains("/"))
                            {
                                val splitvalue= tvvalue.split("/")
                                var one= splitvalue[0]
                                var two= splitvalue[1]
                                if(prefix.isNotEmpty())
                                {
                                    one=prefix + one
                                }
                                res?.text= eliminatezero((one.toDouble() / two.toDouble()).toString())
                            }






            } catch (e: ArithmeticException)
            {
                e.printStackTrace()
            }
        }
    }
    private fun eliminatezero(res:String):String
    {
      var value=res
      if (res.contains(".0"))
          value=res.substring(0,res.length-2)
        return value
    }






    }

