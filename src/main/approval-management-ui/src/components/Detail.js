import React, { useState, useEffect } from 'react';
import { Link, useParams, useNavigate } from 'react-router-dom';
import './Detail.css'
import CmntInputComponent from'./ApprDetail/Comment.js'
import GetAtchdFiles from './ApprDetail/AtchdFile.js';

function Detail() {

    // Initialize the STATE to save
    // 1. input from users
    // 2. data that responsed from API

    const { userId, apprDiv, apprId } = useParams();
    const navigate = useNavigate()
    const [datas, setDatas] = useState([]);

    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch(`/api/appr/getApprInfo?apprId=${apprId}`);
                if (!response.ok) {
                    throw new Error('Not responsing...');
                }
                const data = await response.json();
                setDatas(data);
            } catch (error) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };
        fetchData();
    }, [apprId]);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>품의서 로딩 실패했습니다.</div>;
    }

    return (
        <div className="container">
            <h1>품의서</h1>
            <table>
                {/* <thead>
                    <tr>
                    </tr>
                </thead> */}
                <tbody>
                    {datas.map((data, index) => (
                        <React.Fragment key={index}>
                            <tr>
                                <td><strong>제목</strong></td>
                                <td>{data[0]}</td>
                            </tr>
                            <tr>
                                <td><strong>내용</strong></td>
                                <td>{data[1]}</td>
                            </tr>
                            <tr>
                                <td><strong>최초 등록자</strong></td>
                                <td>{data[2]}</td>
                            </tr>
                            <tr>
                                <td><strong>최초 등록 시간</strong></td>
                                <td>{data[3]}</td>
                            </tr>
                            <tr>
                                <td><strong>첨부 파일</strong></td>
                                <td><GetAtchdFiles apprId={apprId} /></td>
                            </tr>
                        </React.Fragment>
                    ))}
                </tbody>
            </table>
            <CmntInputComponent apprId={apprId} userId={userId} apprDiv={apprDiv} />
            <button onClick={() => navigate(-1)}>Back</button>
        </div>
    );

}


    export default Detail;