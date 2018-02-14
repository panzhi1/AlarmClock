package com.strangeman.alarmclock.view;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.strangeman.alarmclock.R;
import com.strangeman.alarmclock.util.MyUtil;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Administrator on 2018/2/13.
 */

public class MyStopWatch extends View {

    /**
     * 是否已经初始化
     */
    private boolean mIsInitialized = false;

    /**
     * 是否秒表已经开始
     */
    private boolean mIsStarted = false;

    /**
     * 秒表时间
     */
    private Calendar mTimeStart;

    /**
     * 显示的秒表时间
     */
    private String mDisplayWatchTime;

    /**
     * 控件宽
     */
    private float mViewWidth;

    /**
     * 控件高
     */
    private float mViewHeight;

    /**
     * 表盘半径
     */
    private float mCircleRadiusWatcher;

    /**
     * 当前角度
     */
    private float mCurrentDegree;

    /**
     * 表盘中心x坐标
     */
    private float mCenterX;

    /**
     * 表盘中心y坐标
     */
    private float mCenterY;

    /**
     * 表盘外圈宽度
     */
    private float mStrokeWidth;

    /**
     * 表盘背景画笔
     */
    private Paint mPaintCircleBackground;

    /**
     * 弧形画笔
     */
    private Paint mPaintArc;

    /**
     * 显示秒表时间画笔
     */
    private Paint mPaintRemainTime;


    /**
     * 辉光效果画笔
     */
    private Paint mPaintGlowEffect;

    /**
     * 秒表时间变化回调
     */
    private MyTimer.OnTimeChangeListener mRemainTimeChangeListener;

    /**
     * 初始化完成回调
     */
    private MyTimer.OnInitialFinishListener mInitialFinishListener;

    /**
     * 矩形范围
     */
    private Rect mRect;

    /**
     * 当前设置的分钟数
     */
    private int mRemainMinute = 0;

    /**
     * 弧形的参考矩形
     */
    private RectF mRectF;

    /**
     * 演示动画
     */
    private boolean isShowingAnimation;

    public MyStopWatch(Context context) {
        super(context);
    }

    public MyStopWatch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyStopWatch(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 初始化
        if (!mIsInitialized) {
            initialize(canvas);
            mIsInitialized = true;
        }
        // 画表盘背景的圆圈
        canvas.drawCircle(mCenterX, mCenterY, mCircleRadiusWatcher, mPaintCircleBackground);
        // 画弧形
        canvas.drawArc(mRectF, -90, mCurrentDegree, false, mPaintArc);
        // 画按钮

        // 设置显示的剩余时间
        setDisplayNumber();
        // 画剩余时间
        canvas.drawText(mDisplayWatchTime, mCenterX - mRect.width() / 2,
                mCenterY + mRect.height() / 2, mPaintRemainTime);

//        LogUtil.d(LOG_TAG, "绘制中");
    }


    /**
     * 初始化
     *
     * @param canvas canvas
     */
    @SuppressWarnings("deprecation")
    private void initialize(Canvas canvas) {
        mTimeStart = Calendar.getInstance();
        mTimeStart.clear();
        // GMT（格林尼治标准时间）一般指世界时.中国时间（GST）与之相差-8小时
        TimeZone tz = TimeZone.getTimeZone("GMT");
        mTimeStart.setTimeZone(tz);
//获取手机相关信息
        float density = getResources().getDisplayMetrics().density;
        mViewWidth = canvas.getWidth();
        mViewHeight = canvas.getHeight();

        mStrokeWidth = 10 * density;

        mCircleRadiusWatcher = mViewWidth / 3;
        mCurrentDegree = 0;
        mCenterX = mViewWidth / 2;
        mCenterY = mViewHeight / 2;


        mPaintCircleBackground = new Paint();
        mPaintArc = new Paint();
        mPaintRemainTime = new Paint();
        mPaintGlowEffect = new Paint();


        // 表盘外圈颜色
        int colorWatcher = getResources().getColor(R.color.white_trans10);
        mPaintCircleBackground.setColor(colorWatcher);
        mPaintCircleBackground.setStrokeWidth(mStrokeWidth);
        mPaintCircleBackground.setStyle(Paint.Style.STROKE);
        mPaintCircleBackground.setAntiAlias(true);

        // 剩余时间颜色
        int colorRemainTime = Color.WHITE;


        mPaintArc.setColor(colorRemainTime);
        mPaintArc.setStrokeWidth(mStrokeWidth);
        mPaintArc.setStyle(Paint.Style.STROKE);
        mPaintArc.setAntiAlias(true);

        mPaintRemainTime.setStyle(Paint.Style.FILL);
        mPaintRemainTime.setAntiAlias(true);
        mRect = new Rect();
        float densityText = getResources().getDisplayMetrics().scaledDensity;
        mPaintRemainTime.setTextSize(60 * densityText);
        mPaintRemainTime.setColor(colorRemainTime);
        mPaintRemainTime.setAntiAlias(true);
        mPaintRemainTime.getTextBounds("00:00", 0, "00:00".length(), mRect);

        //用于绘制圆弧尽头的辉光效果,辉光区域就是dragButton的区域
        mPaintGlowEffect.setMaskFilter(new BlurMaskFilter(2 * mStrokeWidth / 3, BlurMaskFilter.Blur.NORMAL));
        mPaintGlowEffect.setAntiAlias(true);
        mPaintGlowEffect.setColor(colorRemainTime);
        mPaintGlowEffect.setStyle(Paint.Style.FILL);


        mRectF = new RectF(mCenterX - mCircleRadiusWatcher, mCenterY - mCircleRadiusWatcher
                , mCenterX + mCircleRadiusWatcher, mCenterY + mCircleRadiusWatcher);
        //完成初始化回调
        if (mInitialFinishListener != null) {
            mInitialFinishListener.onInitialFinish();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 控件默认宽度（屏幕宽度）
        int defaultViewWidth = (int) (360 * getResources().getDisplayMetrics().density);
        int width = getDimension(defaultViewWidth, widthMeasureSpec);
        int height = getDimension(width, heightMeasureSpec);

        mViewWidth = width;
        mViewHeight = height;

        setMeasuredDimension(width, height);
    }

    public interface OnInitialFinishListener {
        void onInitialFinish();
    }

    /**
     * 根据用户在屏幕划过的轨迹更新角度
     *
     * @param eventX eventX
     * @param eventY eventY
     * @return 角度
     */
    private float getDegree(float eventX, float eventY) {
        // x轴边
        double tx = eventX - mCenterX;
        // y轴边
        double ty = eventY - mCenterY;
        // 开正平方根,求出滑动点到圆心的距离（斜边）
        double t_length = Math.sqrt(tx * tx + ty * ty);
        // 根据反余弦求出弧度
        double radians = Math.acos(ty / t_length);
        // y的坐标轴是反的所以需要用 180-角度 // Math.toDegrees： 根据角度转化为弧度
        float degree = 180 - (float) Math.toDegrees(radians);

        // 当转到负坐标轴一侧
        if (mCenterX > eventX) {
            degree = 180 + (float) Math.toDegrees(radians);
        }

        return degree;
    }

    /**
     * 设置显示的剩余时间
     */
    private void setDisplayNumber() {
        mDisplayWatchTime = MyUtil.formatTime(mTimeStart.get(Calendar.MINUTE),
                mTimeStart.get(Calendar.SECOND));
    }

    /**
     * 取得尺寸
     *
     * @param defaultDimension 默认尺寸
     * @param measureSpec      measureSpec
     * @return 尺寸
     */
    private int getDimension(int defaultDimension, int measureSpec) {
        int result;
        switch (MeasureSpec.getMode(measureSpec)) {
            case MeasureSpec.EXACTLY:
                result = MeasureSpec.getSize(measureSpec);
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(defaultDimension, MeasureSpec.getSize(measureSpec));
                break;
            default:
                result = defaultDimension;
                break;
        }
        return result;
    }
}
