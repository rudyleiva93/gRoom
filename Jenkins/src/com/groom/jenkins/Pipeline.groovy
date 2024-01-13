package com.groom.jenkins

import com.groom.jenkins.pipelines.GroomPipeline

def groomPipeline() {
    GroomPipeline pipeline = new GroomPipeline()
    pipeline.build()
}

return this;