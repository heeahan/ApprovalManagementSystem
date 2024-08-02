import React, { useEffect, useState } from "react";
import axios from "axios";

const GetAtchdFiles = ({ apprId }) => {
    const [ file, setFile ] = useState([]);
    const [ fileString, setFileString] = useState('');
    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get(`/api//appr/getAtchdFilesName?apprId=${apprId}`);
                // console.log(response.data);
                setFile(response.data);
                // console.log(file);
            }
            catch (error) {
                console.error('error', error);
            }
        };
        fetchData();
    }, [apprId]);

    useEffect(() => {
        if (file.length > 0){
            setFileString(file.join(', '));
        }
    }, [file]);

    return(
        <div>{fileString}</div>
    )
}


export default GetAtchdFiles;