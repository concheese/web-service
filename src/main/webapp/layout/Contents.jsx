import styled from "styled-components";

const Container = styled.div`
  padding-top: 50px;
`;

export default function Contents({ children }) {
  return <Container>{children}</Container>;
}
