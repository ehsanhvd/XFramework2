package com.hvd.xview.view.expandablelayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;


/**
 * Created by SilenceDut on 16/6/6.
 */

public class ExpandableLayout extends LinearLayout {
    static final int PRE_INIT = -1;
    static final int CLOSED = 0;
    static final int EXPANDED = 1;
    public float ROTATION_ANGLE = -90;
    private int mExpandState;

    public ExpandableLayout(Context context) {
        super(context);
        init();
    }

    public ExpandableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ExpandableLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public static void rotate(final View view, Float from, Float to) {
        Animation animation = new RotateAnimation(from, to, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
        animation.setDuration(300);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }

    private void init() {
        setClickable(true);
        setOrientation(VERTICAL);
        this.setClipChildren(false);
        this.setClipToPadding(false);
        mExpandState = PRE_INIT;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        if (childCount != 2) {
            throw new IllegalStateException("ExpandableLayout must has two child view !");
        }
    }

    //TODO doesnt work on anko
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View expandableView = getChildAt(1);
        if (expandableView instanceof WebView) {
            //if u use webview as expandableview, you should call collapseImmediately() or expandImmediately() after webview load finished
            getChildAt(1).setVisibility(INVISIBLE);
        } else {
            getChildAt(1).setVisibility(GONE);
        }
        mExpandState = CLOSED;
    }

    public void expand() {
        if (mExpandState == EXPANDED) {
            return;
        }
        ImageView imgInd = (ImageView) findViewWithTag("imgIndicator");
        rotate(imgInd, 0f, ROTATION_ANGLE);

        final View expandLayout = getChildAt(1);
        expandLayout.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        final int targetHeight = expandLayout.getMeasuredHeight();

        expandLayout.getLayoutParams().height = 1;
        expandLayout.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                expandLayout.getLayoutParams().height = interpolatedTime == 1
                        ? LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                expandLayout.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (targetHeight / expandLayout.getContext().getResources().getDisplayMetrics().density));
        expandLayout.startAnimation(a);
        mExpandState = EXPANDED;
    }

    public void collapse() {
        if (mExpandState == CLOSED) {
            return;
        }
        ImageView imgInd = (ImageView) findViewWithTag("imgIndicator");
        rotate(imgInd, ROTATION_ANGLE, 0f);
        final View v = getChildAt(1);
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
        mExpandState = CLOSED;
    }

    public boolean isExpanded() {
        return mExpandState == EXPANDED;
    }

    public void toggle() {
        if (mExpandState == EXPANDED) {
            collapse();
        } else if (mExpandState == CLOSED) {
            expand();
        }
    }

    public void expandImmediately() {
        ImageView imgInd = (ImageView) findViewWithTag("imgIndicator");
        imgInd.setRotation(ROTATION_ANGLE);
        getChildAt(1).setVisibility(VISIBLE);
        mExpandState = EXPANDED;
    }


    public void collapseImmediately() {
        ImageView imgInd = (ImageView) findViewWithTag("imgIndicator");
        imgInd.setRotation(0);
        getChildAt(1).setVisibility(GONE);
        mExpandState = CLOSED;
    }

    @Override
    public boolean performClick() {
        toggle();
        return super.performClick();
    }
}