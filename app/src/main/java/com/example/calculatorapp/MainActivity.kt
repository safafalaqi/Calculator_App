package com.example.calculatorapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*


class MainActivity : AppCompatActivity() {
    lateinit var inputList:ArrayList<String>
    lateinit var display:TextView
    lateinit var button7:Button
    lateinit var button8:Button
    lateinit var button9:Button
    lateinit var buttonDiv:Button
    lateinit var button4:Button
    lateinit var button5:Button
    lateinit var button6:Button
    lateinit var buttonMul:Button
    lateinit var button1:Button
    lateinit var button2:Button
    lateinit var button3:Button
    lateinit var buttonMinus:Button
    lateinit var buttonSign:Button
    lateinit var button0:Button
    lateinit var buttonDot:Button
    lateinit var buttonPlus:Button
    lateinit var buttonDel:Button
    lateinit var buttonC:Button
    lateinit var buttonEqual:Button
    lateinit var table:TableLayout
    var operator= ""
    var firstNum = ""
    var secondtNum= ""
    var result = 0f
    var isDecimal=false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        display=findViewById(R.id.tvDisplay)

        button7 = findViewById(R.id.bt7)
        button7.setOnClickListener{
            numClicked("7")
        }
        button8 = findViewById(R.id.bt8)
        button8.setOnClickListener {
            numClicked("8")
        }
        button9 = findViewById(R.id.bt9)
        button9.setOnClickListener {
            numClicked("9")
        }
        buttonDiv = findViewById(R.id.btDev)
        buttonDiv.setOnClickListener {
            setoperator("/")
        }
        button4 = findViewById(R.id.bt4)
        button4.setOnClickListener {
            numClicked("4")
        }
        button5 = findViewById(R.id.bt5)
        button5.setOnClickListener {
            numClicked("5")
        }
        button6 = findViewById(R.id.bt6)
        button6.setOnClickListener {
            numClicked("6")
        }
        buttonMul = findViewById(R.id.btMul)
        buttonMul.setOnClickListener {
            setoperator("*")
        }
        button1 = findViewById(R.id.bt1)
        button1.setOnClickListener {
            numClicked("1")
        }
        button2 = findViewById(R.id.bt2)
        button2.setOnClickListener {
            numClicked("2")
        }
        button3 = findViewById(R.id.bt3)
        button3.setOnClickListener {
            numClicked("3")
        }
        buttonMinus = findViewById(R.id.btMinus)
        buttonMinus.setOnClickListener {
            setoperator("-")
        }
        buttonSign = findViewById(R.id.btSign)
        buttonSign.setOnClickListener {
            signClicked()
        }
        button0 = findViewById(R.id.bt0)
        button0.setOnClickListener {
            numClicked("0")
        }
        buttonDot = findViewById(R.id.btPoint)
        buttonDot.setOnClickListener {
            isDecimal()
        }
        buttonPlus = findViewById(R.id.btPlus)
        buttonPlus.setOnClickListener{
            setoperator("+")
        }
        buttonDel = findViewById(R.id.btDEL)
        buttonDel.setOnClickListener{
            delClicked()
        }
        buttonC = findViewById(R.id.btC)
        buttonC.setOnClickListener{
            clearClicked()

        }
        buttonEqual = findViewById(R.id.btEqual)
        buttonEqual.setOnClickListener{
            calculate(firstNum,operator,secondtNum)
            display.text=result.toString()
            operator =""
            firstNum = ""
            secondtNum = ""
        }

    }


    fun setoperator(op:String)
    {
        //if result is not 0 apply to next operation
        if(result!=0f){
            firstNum = result.toInt().toString()
        }
        operator=op
        display.text = firstNum + op

    }
    fun isDecimal(){
        if(operator==""&&!firstNum.contains("."))
        {if(firstNum.isNotEmpty())
            numClicked(".")
        else
        {
            firstNum="0"
            numClicked(".")
        }}
        if(operator!=""&&!secondtNum.contains("."))
        {numClicked(".")}
    }
    //to parse the number clicked
    fun numClicked(number:String)
    {
        //if first number clicked then set as firstNum
        if(operator=="")
        {
                firstNum += number
                display.text = firstNum

        }
          else{
            secondtNum += number
            display.text=firstNum+operator+secondtNum
        }
    }
    //to calculate
    fun calculate(fisrtNum: String, operator: String, secondtNum: String) {

        when(operator) {
            "+" -> result = fisrtNum.toFloat() + secondtNum.toFloat()
            "-" -> result = fisrtNum.toFloat() - secondtNum.toFloat()
            "*" -> result = fisrtNum.toFloat() * secondtNum.toFloat()
            "/" ->{
                if(secondtNum!="0") {
                    result = fisrtNum.toFloat() / secondtNum.toFloat()
                }else
                    Toast.makeText(this,"Can not divide by 0 ",Toast.LENGTH_LONG).show()
                    clearClicked()
                }
            }
        }


    //to clear user input
    fun clearClicked(){
        operator =""
        firstNum = ""
        secondtNum = ""
        result = 0f
        display.text ="0"
        isDecimal=false
    }
    //to add negative sign
    fun signClicked()
    {
        if(operator==""&& firstNum.isEmpty())
            numClicked("-")
        if(operator!=""&&secondtNum.isEmpty() && operator!="-")
            numClicked("-")
    }
    //to delete
    fun delClicked()
    {
       if(secondtNum.isNotEmpty()){
           display.text=display.text.substring(0,(firstNum.length)+(operator.length))
           secondtNum=""
       }else if(operator.isNotEmpty()){
           display.text=display.text.substring(0,firstNum.length)
           operator=""
       }
           else{
               display.text="0"
               firstNum=""
           }

       }

}