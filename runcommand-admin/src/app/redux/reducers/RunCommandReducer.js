import {
    RUN_CMD_DATA,
    RUN_UPLOAD_FILE_DATA,
    RUN_UPLOAD_ERROR
} from '../actions/RunCommandActions'

const initialState = {
    commandResponse:null
}

const RunCommandReducer = function (state = initialState, action) {
    switch (action.type) {
        case RUN_CMD_DATA: {
            return {
                ...state,
                commandResponse: {...action.payload},
            }
        }
        case RUN_UPLOAD_FILE_DATA: {
            return {
                ...state,
                commandResponse: {...action.payload},
            }
        }
        case RUN_UPLOAD_ERROR: {
            return {
                ...state,
                commandResponse: {...action.payload},
            }
        }
        default: {
            return {
                ...state,
            }
        }
    }
}

export default RunCommandReducer
