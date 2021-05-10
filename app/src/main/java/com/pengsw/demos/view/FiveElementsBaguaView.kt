package com.pengsw.demos.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.pengsw.demos.drawTextWithCenterPoint
import kotlin.random.Random


/**
 * @date      2021/4/30
 * @author    Pengshuwen
 * @describe  八卦图
 */
class FiveElementsBaguaView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr)  {


    private var centerX: Float = 0f
    private var centerY: Float = 0f

    private var mWidth =0
    private val radius=170f;

    private var currentAngle=0f


    private val redColor=Color.parseColor("#ff0000")
    private  val blackColor=Color.parseColor("#000000")
    private  val whiteColor=Color.parseColor("#ffffff")

    private var mCall:(()->Unit)?=null


    private var blackPaint=Paint().apply {
        color = blackColor
        isAntiAlias=true
    }

    private var whitePaint=Paint().apply {
       color = whiteColor
        isAntiAlias=true
    }

    private var blackPaint2=Paint().apply {
        color =blackColor
        isAntiAlias=true
        strokeWidth=20f
    }

    private var textPaint=Paint().apply {
        color =blackColor
        isAntiAlias=true
        setTypeface(Typeface.DEFAULT_BOLD)
        textSize=30f
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
         if (canvas==null)return



      centerX=mWidth/2f
        centerY=mWidth/2f

        drawYinAndYang(canvas)
        drawGossip(canvas)

    }


    private fun drawYinAndYang(canvas: Canvas) {


        canvas.save()
        canvas.rotate(currentAngle,centerX,centerY)

        blackPaint.strokeWidth=7f
        blackPaint.style=Paint.Style.STROKE
        canvas?.drawCircle(centerX,centerY,radius,blackPaint)

       val rect=RectF(centerX-radius,centerY-radius,centerX+radius,centerY+radius)
       blackPaint.style=Paint.Style.FILL
       canvas?.drawArc(rect,270f,180f,true,blackPaint)
        canvas.drawCircle(centerX,centerY-(radius/2),radius/2,blackPaint)

       whitePaint.style=Paint.Style.FILL
       canvas.drawCircle(centerX,centerY+(radius/2),radius/2,whitePaint)

       canvas.drawCircle(centerX,centerY+(radius/2),radius/5,blackPaint)
       canvas.drawCircle(centerX,centerY-(radius/2),radius/5,whitePaint)

        canvas.restore()

    }



    private fun drawGossip(canvas: Canvas) {

        // canvas.rotate(90f)
        val angle=360/hangs.size
        hangs.forEach {
            canvas.rotate(angle.toFloat(),centerX,centerY)
           drawLine1(canvas,it.isLine1,30f)
            drawLine1(canvas,it.isLine2,60f)
            drawLine1(canvas,it.isLine3,90f)
            canvas.drawTextWithCenterPoint(centerX.toInt(), (centerY-(radius+130)).toInt(),it.text,textPaint);
        }
    }
    private fun drawLine1(canvas: Canvas, isLine:Boolean, y:Float){
        if (isLine){
            blackPaint2.color=redColor
            canvas.drawLine(centerX-60f,centerY-(radius+y),centerX+60f,centerY-(radius+y),blackPaint2)
        }else{
            blackPaint2.color=blackColor
            canvas.drawLine(centerX-10f,centerY-(radius+y),centerX-60f,centerY-(radius+y),blackPaint2)
            canvas.drawLine(centerX+10f,centerY-(radius+y),centerX+60f,centerY-(radius+y),blackPaint2)
        }
    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mWidth=width
    }



    fun start(){

        val target=Random(1).nextInt(680,2489)
        val ainm=ValueAnimator.ofInt(0,target)
        ainm.duration=3000
        ainm.addUpdateListener {
           currentAngle=( it.animatedValue as Int).toFloat()
            invalidate()
            if (currentAngle>=target){
                mCall?.invoke()
            }

        }
        ainm.start()
    }


    fun setResultListener(call:()->Unit){
        this.mCall=call
    }



        var hangs= mutableListOf<Hang>()?.apply {


            add(Hang().apply {
                isLine1=false
                text="巽"
            })
           add(Hang().apply {
                isLine1=false
                isLine3=false
               text="坎"
            })
            add(Hang().apply {
                isLine1=false
                isLine2=false
                text="艮"
            })
           add(Hang().apply {
                isLine1=false
                isLine2=false
                isLine3=false
               text="坤"
            })
            add(Hang().apply {
                isLine2=false
                isLine3=false
                text="震"
            })
           add(Hang().apply {
                isLine2=false
                text="离"
            })
            add(Hang().apply {
                isLine3=false
                text="兑"
            })
            add(Hang().apply {
                text="乾"
            })
        }





    class Hang{
        var isLine1=true;
        var isLine2=true;
        var isLine3=true;
        var text="乾"
    }

}