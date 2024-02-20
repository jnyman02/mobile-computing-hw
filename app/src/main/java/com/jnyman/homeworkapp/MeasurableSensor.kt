package com.jnyman.homeworkapp

abstract class MeasurableSensor(
    protected val sensorName: String,
    protected val sensorType: Int,
) {

    protected  var onSensorValueChange: ((List<Float>) -> Unit)? = null

    abstract val doesSensorExist: Boolean

    abstract fun startListening()
    abstract fun stopListening()

    fun setOnSensorValueChangeListener(listener: (List<Float>) -> Unit) {
        this.onSensorValueChange = listener
    }
}